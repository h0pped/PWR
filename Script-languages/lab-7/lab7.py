from datetime import datetime
import logging
import os
import sys
import re
import ipaddress
from enum import Enum


class MalformedHTTPRequestException(Exception):
    """Malformed HTTP Request"""
    pass


class Month(Enum):
    Jan = 1
    Feb = 2
    Mar = 3
    Apr = 4
    May = 5
    Jun = 6
    Jul = 7
    Aug = 8
    Sep = 9
    Oct = 10
    Nov = 11
    Dec = 12


def string_to_datetime(date):
    datetimeRegex = r'(\d+)\/(\w+)\/(\d+):(\d+):(\d+):(\d+) ([+-]\d+)'
    res = re.match(datetimeRegex, date)
    if res is not None:
        year = int(res.group(3))
        month = int(Month[res.group(2)].value)
        day = int(res.group(1))
        hour = int(res.group(4))
        minute = int(res.group(5))
        second = int(res.group(6))
        dt = datetime(year, month, day, hour, minute, second)
        return dt


class LogHTTPRequest:
    method = ""
    path = ""
    status_code = 0

    def __init__(self, _method, _path, _status_code):
        self.method = _method
        self.path = _path
        self.status_code = _status_code

    def __str__(self):
        return "METHOD: " + self.method+"\nPATH: " + self.path + "\nCODE: "+str(self.status_code)

    def get_method(self):
        return self.method

    def get_path(self):
        return self.path

    def status_code(self):
        return self.status_code


class LogEntry:
    def __init__(self, ip, httprequest, date):
        self.ip = ip
        self.httprequest = httprequest
        self.date = string_to_datetime(date)

    def __str__(self):
        return "----------\nIP: "+str(self.ip)+"\n"+str(self.httprequest)+"\n"+str(self.date)+"\n----------"


def log_to_logEntry(log):
    ipRegex = r'((\d+).(\d+).(\d+).(\d+)) - - '
    dateRegex = r'\b((\d{2})\/(\w{3})\/(\d{4}):(\d{2}:\d{2}:\d{2} [+-]\d{4}))\b'
    requestRegex = r'"(\w+) ((\/{1}[^ ]*){1,}) '
    statusCodeRegex = r' (\d+) '

    ipRes = re.match(ipRegex, log)
    if ipRes is not None:
        ip = ipRes.group(1)
        requestRes = re.search(requestRegex, log)
        if requestRes is not None:
            method = requestRes.group(1)
            path = requestRes.group(2)
            statusCodeRes = re.search(statusCodeRegex, log)
            if statusCodeRes is not None:
                statusCode = statusCodeRes.group(1)
                httpRequest = LogHTTPRequest(method, path, statusCode)
                dateRes = re.search(dateRegex, log)
                if dateRes is not None:
                    date = dateRes.group(1)
                    entry = LogEntry(ip, httpRequest, date)
                    return entry
    raise MalformedHTTPRequestException


def logs_to_EntryList(filename):
    entries = []
    exceptions_count = 0
    try:
        with open(filename) as logs:
            for line in logs:
                try:
                    entries.append(log_to_logEntry(line))
                except MalformedHTTPRequestException:
                    exceptions_count += 1
            print("ERROR LINES COUNT: ", exceptions_count)
    except EnvironmentError:
        print("No such file or directory")
    return entries


def requests_between_time(dt1, dt2, entries):
    if dt2 > dt1:
        for x in entries:
            if x.date > dt1 and x.date < dt2:
                print(x)
    else:
        print("WRONG TIME")


def run():
    entries = logs_to_EntryList("access_log-20201025")
    time1 = string_to_datetime("20/Oct/2020:13:33:58 +0200")
    time2 = string_to_datetime("20/Oct/2020:14:38:58 +0200")
    requests_between_time(time1, time2, entries)


if __name__ == "__main__":
    run()
