import os
import sys
from tkinter.constants import ANCHOR, NW
from typing import Annotated
from numpy import imag
import requests
import sqlite3
import tkinter as tk
import matplotlib.pyplot as plt
from PIL import ImageTk, Image
from matplotlib.backends.backend_tkagg import (
    FigureCanvasTkAgg, NavigationToolbar2Tk)


def fetch_data():
    response = requests.get("https://api.punkapi.com/v2/beers")
    return response.json()


def db_clear_content(conn, cursor):
    cursor.execute("""
        DELETE FROM beers 
    """)
    conn.commit()
    print("---TABLE WAS DROPPED SUCCESFULLY---")


def create_table(conn, cursor):
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS beers(
            id INTEGER PRIMARY KEY,
            name TEXT,
            tagline TEXT,
            description TEXT,
            abv REAL
        )
    """)
    conn.commit()


def insert_into_table(conn, cursor, data):
    cursor.executemany("""
        INSERT INTO beers(name,tagline,description,abv) VALUES (?, ?, ?, ?)
    """, data)
    conn.commit()


def read_from_table(cursor):
    cursor.execute("""
        SELECT * FROM beers
    """)
    data = cursor.fetchall()
    return data


def avg_abv(cursor):
    cursor.execute("""
        SELECT AVG(abv) from beers
    """)
    avg_abv_data = cursor.fetchone()
    return avg_abv_data[0]


class Application(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.pack()
        self.create_widgets()
        master.title("My application")
        master.geometry('1200x700')

    def create_widgets(self):
        self.header = tk.Label(self)
        self.header['text'] = "Lab 12: Work with API"
        self.header['width'] = 50
        self.header.pack(side="top")

        self.message = tk.Label(self)
        self.message['text'] = "Message: "
        self.message['width'] = 50
        self.message.pack(side="bottom")

        self.aggregation_label = tk.Label(self)
        self.aggregation_label['text'] = "Aggregation: "
        self.aggregation_label.pack(side="top")

        self.fetch_data_button = tk.Button(self)
        self.fetch_data_button["text"] = "Fetch and store data"
        self.fetch_data_button['width'] = 70
        self.fetch_data_button['bg'] = "SpringGreen1"
        self.fetch_data_button["command"] = self.fetch_and_store_data
        self.fetch_data_button.pack(side="top")

        self.aggregation_data_button = tk.Button(self)
        self.aggregation_data_button["text"] = "Average ABV from data"
        self.aggregation_data_button['width'] = 70
        self.aggregation_data_button['bg'] = "SpringGreen2"
        self.aggregation_data_button["command"] = self.aggregation
        self.aggregation_data_button.pack(side="top")

        self.show_plot = tk.Button(self)
        self.show_plot["text"] = "Show plot!"
        self.show_plot['width'] = 70
        self.show_plot['bg'] = "SpringGreen1"
        self.show_plot["command"] = self.show
        self.show_plot.pack(side="top")

        self.quit = tk.Button(self, text="QUIT", fg="red",
                              command=self.master.destroy)
        self.quit.pack(side="bottom")

    def say_hi(self):
        print("hi there, everyone!")

    def update_message(self, msg):
        self.message["text"] = "Message: " + msg

    def aggregation(self):
        try:
            res = avg_abv(cursor)
            self.aggregation_label["text"] = f"Aggregation (Average ABV): {round(res, 2)}"
            self.update_message("Get aggregation inforamtion")
        except Exception:
            print("Error")

    def fetch_and_store_data(self):
        try:
            db_clear_content(conn, cursor)
        except sqlite3.OperationalError:
            print("---Table is clear---")
        js = fetch_data()
        self.update_message("Data fetched")
        self.beers = []
        for x in js:
            beer = (x["name"], x["tagline"], x['description'], x['abv'])
            self.beers.append(beer)
        create_table(conn, cursor)
        self.update_message("Table created")
        insert_into_table(conn, cursor, self.beers)
        self.update_message("Values succesfully inserted into table")
        print(read_from_table(cursor))

    def show(self):
        try:
            abvs = []
            ids = list(range(1, len(self.beers)+1))
            for x in self.beers:
                abvs.append(x[3])
            plt.figure(figsize=(10, 5))
            plt.bar(ids, abvs)
            plt.xlabel("ID")
            plt.ylabel("ABV")
            plt.savefig('plot.png')
            global img
            global im
            img = Image.open("plot.png")
            img = img.resize((1100, 500), Image.NEAREST)
            im = ImageTk.PhotoImage(img)
            self.canv = tk.Canvas(self.master, width=1100,
                                  height=500, bg="black")
            self.canv.pack()
            my_img = self.canv.create_image(0, 0, anchor=NW, image=im)
            self.update_message("Show a plot")
        except Exception:
            print("Error")


def run():
    global conn
    conn = sqlite3.connect(":memory:")
    global cursor
    cursor = conn.cursor()
    root = tk.Tk()
    app = Application(master=root)
    app.mainloop()


if __name__ == "__main__":
    run()
