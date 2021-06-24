import logging
import os
import sys
import re
import ipaddress


def read_config():
    global fileName
    global debug
    global display
    config = dict()
    lines = []
    headerRegex = r'^\[(\w+)\]$'
    attributeRegex = r'^([a-zA-Z0-9-. ]+)=(.+)$'
    currentAttr = ''
    with open("lab6.config") as conf:
        lines = [line.rstrip() for line in conf]
    for line in lines:
        res = re.match(headerRegex, line)
        if res is not None:
            currentAttr = res.group(1)
            config[currentAttr] = dict()
        else:
            res = re.match(attributeRegex, line)
            if res is not None:
                if(currentAttr != ''):
                    config[currentAttr][res.group(1)] = res.group(2)
    display = config['Display']
    fileName = config["LogFile"]["name"]
    if 'lines' not in display.keys():
        display['lines'] = 5
    if 'separator' not in display.keys():
        display['separator'] = "|"
    if 'filter' not in display.keys():
        display['filter'] = "GET"

    if 'debug' not in config.keys():
        config['debug'] = "INFO"
    debug = config['debug']


def read_logs():
    logs = dict()
    lines = []
    try:
        with open(fileName) as logs:
            lines = [line.rstrip() for line in logs]
    except EnvironmentError:
        print("No such file or directory")
    return lines


def parse_line(x):
    ipRegex = r'\b((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\b'
    dateRegex = r'\b((\d{2})\/(\w{3})\/(\d{4}):(\d{2}:\d{2}:\d{2} [+-]\d{4}))\b'
    pathRegex = r' (\/{1}[^ ]*){1,} '
    responseStatusRegex = r' (\d{3}) '
    ip = re.match(ipRegex, x)  # .group(1)
    date = re.search(dateRegex, x)  # .group(1)
    path = re.search(pathRegex, x)  # .group(1).rstrip()
    response = re.search(responseStatusRegex, x)  # .group(1).rstrip()
    if ip is None:
        ip = "-"
    else:
        ip = ip.group(1)
    if date is None:
        date = "-"
    else:
        date = date.group(1)
    if path is None:
        path = "-"
    else:
        path = path.group(1).rstrip()
    if response is None:
        response = 0
    else:
        response = int(response.group(1).rstrip())
    return (ip, date, path, response)


def analyze_data(logs):
    analyzed = []
    for x in logs:
        analyzed.append(parse_line(x))
    return analyzed

# 245693 % 16 + 8 = 21 --> 255.255.248.0


def is_ip_belongs_to_subnet(ip):
    address = ipaddress.ip_address(ip)
    a_network = ipaddress.ip_network('82.129.0.0/21')
    return address in a_network


def check_subnet(data):
    display_lines = int(display["lines"])
    count = 0
    for x in data:
        ip = x[0]
        if is_ip_belongs_to_subnet(ip) is True:
            if count < display_lines:
                print(ip)
                count += 1
            else:
                os.system("pause")
                count = 0


def total_bytes_sent(logs):
    filter = display['filter']
    separator = display['separator']
    total_bytes = 0
    filterRegex = r'\b('+filter+r')\b'
    bytesRegex = r' \d+ +(\d+) '
    counter = 0
    for x in logs:
        filterLine = re.search(filterRegex, x)
        if filterLine is not None:
            counter += 1
            total_bytes += int(re.search(bytesRegex, x).group(1))
    print(filter, separator, total_bytes)


def run():
    read_config()
    logs = read_logs()
    tuples = analyze_data(logs)
    check_subnet(tuples)
    total_bytes_sent(logs)


if __name__ == "__main__":
    run()


# PS C:\Staff\PWR\semester-4\Script-languages\lab-6> pycodestyle lab6.py
# lab6.py:21:16: E711 comparison to None should be 'if cond is not None:'
# lab6.py:26:20: E711 comparison to None should be 'if cond is not None:'
# lab6.py:55:80: E501 line too long (112 > 79 characters)
# lab6.py:56:80: E501 line too long (80 > 79 characters)
# lab6.py:102:40: E712 comparison to True should be 'if cond is True:' or 'if cond:'
# lab6.py:110:80: E501 line too long (278 > 79 characters)
# lab6.py:122:23: E711 comparison to None should be 'if cond is not None:'

# PS C:\Staff\PWR\semester-4\Script-languages\lab-6> pycodestyle lab6.py
# lab6.py:55:80: E501 line too long (112 > 79 characters)
# lab6.py:56:80: E501 line too long (80 > 79 characters)

# These lines are Regex expressions
