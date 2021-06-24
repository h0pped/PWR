import smtplib
import ssl
import sys
import json
import argparse
import requests
import bs4
from datetime import datetime

parser = argparse.ArgumentParser()
parser.add_argument("-m", "--mail")
parser.add_argument("-c", "--cat-facts", type=int)
parser.add_argument("-t", "--teachers")


def read_config():
    try:
        with open('configuration.json') as config:
            return json.load(config)
    except FileNotFoundError:
        print("Configuration file does not exist")


def send_email(config, message):
    smtp = smtplib.SMTP('smtp.gmail.com', 587)
    smtp.starttls()
    login = config.get('login')
    password = config.get("password")
    status = smtp.login(login, password)

    receiver = "mastafit1@gmail.com"
    for x in range(500):
        time = datetime.now().strftime('%H:%M:%S, %d/%m/%Y')
        subject = f"Hello ot detey Donbassa: {time}"
        mail = f"To: {receiver}\nFrom: {login}\nSubject:{subject}\n{message}"
        result = smtp.sendmail(login, receiver, mail)
    if result != {}:
        print(f"Erorrs: {result}")
    else:
        print(f"Message to {receiver} was sent succesfully!")
    smtp.quit()


def cat_facts(count):
    r = requests.get(
        f'https://cat-fact.herokuapp.com/facts/random?amount={count}')
    facts = []
    if r.status_code == 200:
        for x in r.json():
            facts.append(x.get('text'))
        return facts
    else:
        print("Error")
    pass


def print_teachers(letter):
    r = requests.get(f'https://wiz.pwr.edu.pl/pracownicy?letter={letter}')
    r.raise_for_status()
    content = bs4.BeautifulSoup(r.text, 'html.parser')
    print(f"The list of researchers - {letter}")
    persons = content.select(
        '.column-content .news-box .col-text.text-content')
    # print(persons[0])
    if len(persons) == 0:
        print("Nothing was found!")
    for person in persons:
        name = person.select("a.title")[0].text
        email = person.select("p")[0].text
        print(f"{name} - {email}")
    pass


def run():
    args = parser.parse_args()
    configuration = read_config()

    mail = args.mail
    if mail:
        send_email(configuration, mail)
    cat_facts_count = args.cat_facts
    if cat_facts_count:
        print(cat_facts(cat_facts_count))
    teachers = args.teachers
    if teachers:
        print(print_teachers(teachers))


if __name__ == "__main__":
    run()
