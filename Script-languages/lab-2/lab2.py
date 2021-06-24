import sys
import fileinput
import logging


def printPaths(arr):
    print("Paths: ")
    for x in arr:
        print(x)


def largestResourceIndex(arr):
    return arr.index(max(arr))


def numberOfFailed(arr):
    return arr.count('404')


def totalSentBytes(arr):
    bytes = 0
    for x in arr:
        bytes += int(x)
    return bytes


def totalSentKiloBytes(arr):
    bytes = 0
    for x in arr:
        bytes += int(x)
    return bytes/1024


def meanProcessTime(arr):
    return sum(arr)/len(arr)


def main():
    logging.basicConfig(level=logging.INFO)
    logging.info("---PROGRAM START---")

    paths = []
    status_codes = []
    sent_bytes = []
    processing_times = []

    logging.debug("---READING FROM INPUT AND PARSING DATA---")
    for line in fileinput.input():
        paths.append(line.rstrip().split()[0])
        status_codes.append(line.rstrip().split()[1])
        sent_bytes.append(line.rstrip().split()[2])
        processing_times.append(int(line.rstrip().split()[3]))

    if(len(paths) != 0):  # if input is not empty
        logging.info("---PATHS---")
        for path in paths:
            logging.info(path)

        logging.info("---PRINTING DATA---")
        print("\n---------------------------------")
        largestResouce = largestResourceIndex(sent_bytes)
        print("Largest Resource: ",
              paths[largestResouce],
              sent_bytes[largestResouce])
        print("Number Of Failed:", numberOfFailed(status_codes))
        print("Total Sent Bytes:", totalSentBytes(sent_bytes))
        print("Total Sent KiloBytes:", totalSentKiloBytes(sent_bytes))
        print("Mean Process Time:", meanProcessTime(processing_times))
        print("---------------------------------\n")
    logging.info("---END---")


if __name__ == '__main__':
    main()


# https://docs.python.org/3/library/logging.html
# https://docs.python.org/3/howto/logging.html
# https://docs.python.org/3/howto/logging-cookbook.html
