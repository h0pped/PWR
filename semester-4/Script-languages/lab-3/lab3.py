import sys
import fileinput
import logging
import os


def read_log():
    logs = []
    lines_count = 0
    for line in fileinput.input():
        lines_count += 1
        if(line.isspace() != True):  # if not empty line
            path = line.rstrip().split()[0]
            status_code = int(line.rstrip().split()[1])
            sent_bytes = int(line.rstrip().split()[2])
            processing_time = int(line.rstrip().split()[3])
            x = (path, status_code, sent_bytes, processing_time)
            logs.append(x)
    logging.debug("LINES READ: " + str(lines_count))
    logging.debug("LIST ENTRIES: " + str(len(logs)))

    return logs


def succesfull_reads(logs):
    succesfull = []
    for log in logs:
        if(log[1] >= 200 and log[1] < 300):
            succesfull.append(log)
    logging.info("LIST SUCCESFULL ENTRIES: " + str(len(succesfull)))
    return succesfull


def failed_reads(logs):
    failed_400 = []
    failed_500 = []
    for log in logs:
        if(log[1] >= 400 and log[1] < 500):
            failed_400.append(log)
        elif(log[1] >= 500 and log[1] < 600):
            failed_500.append(log)
    logging.info("LIST 4xx ERROR CODE ENTRIES: " + str(len(failed_400)))
    logging.info("LIST 5xx ERROR CODE ENTRIES: " + str(len(failed_500)))
    return failed_400+failed_500


def html_entries(logs):
    entries = []
    for log in logs:
        if log[0].endswith(".html"):
            entries.append(log)
    return entries


def print_html_entries(logs):
    print(html_entries(logs))


def run():
    logging.basicConfig(level=logging.DEBUG)
    logging.info("---PROGRAM START---")
    print("\n------------------------------------------------------------\n")
    logs = read_log()
    print(logs)
    print("\n------------------------------------------------------------\n")
    succesfull = succesfull_reads(logs)
    print(succesfull)
    print("\n------------------------------------------------------------\n")
    failed = failed_reads(logs)
    print(failed)
    print("\n------------------------------------------------------------\n")
    print_html_entries(logs)


if os.isatty(sys.stdin.fileno()) == False:
    run()

else:
    print("FALSE")

# https://docs.python.org/3/library/logging.html
# https://docs.python.org/3/howto/logging.html
# https://docs.python.org/3/howto/logging-cookbook.html
