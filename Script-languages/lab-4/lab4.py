import sys
import fileinput
import logging
import os


def read_log():
    dictionary = {}
    for line in fileinput.input():
        split = line.split()
        ip = split[0]
        if ip in dictionary:  # check if we already have a key with that ip
            # appending to dictionary everything except ip
            dictionary.get(ip).append(' '.join(split[3:]))
        else:
            # appending to dictionary everything except ip
            dictionary[ip] = [' '.join(split[3:])]
    return dictionary


def ip_requests_number():
    dictionary = {}
    for log in logs:
        dictionary[log] = len(logs.get(log))
    return dictionary


def ip_find(most_active=True):
    all_values = ip_requests_number()  # getting the number of requests for each ip
    if most_active == True:
        max_key = max(all_values, key=all_values.get)
        return (max_key, all_values[max_key])
    else:
        min_key = min(all_values, key=all_values.get)
        return (min_key, all_values[min_key])


def longest_request():
    requests = {}
    for ip in logs:
        for log in logs[ip]:
            if ip in requests:
                # getting the path (rssplit to remove "HTTP/1.1" from string)
                requests.get(ip).append(log.split('"')[1].rsplit(" ", 1)[0])
            else:
                requests[ip] = [log.split('"')[1].rsplit(" ", 1)[0]]
    max_length = ("", "")
    for key, val in requests.items():
        for v in val:
            if(len(v) > len(max_length[1])):
                max_length = (key, v)
    return max_length


def non_existent():
    errors = []
    for key, val in logs.items():
        for v in val:
            path = v.split('"')[1].rsplit(" ", 1)[0]  # parsing the path
            # parsing the status Code
            statusCode = int(v.split('"')[2].split()[0])
            if path not in errors:  # checking if we don't have this path in our list
                if(statusCode == 404):
                    # if 'Page not found' then append it to our list
                    errors.append(path)
    return errors


def run():
    global logs
    logs = read_log()
    print("AMOUNT OF REQUESTS: ", ip_requests_number())
    print()
    print("LARGEST NUMBER OF REQUESTS: ", ip_find())
    print()
    print("LOWEST NUMBER OF REQUESTS: ", ip_find(most_active=False))
    print()
    print("LONGEST REQUEST : ", longest_request())
    print()
    print("PAGE NOT FOUND REQUESTS: ", non_existent())


if os.isatty(sys.stdin.fileno()) == False:
    run()

else:
    print("FALSE")
