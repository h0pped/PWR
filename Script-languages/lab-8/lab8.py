import sys
import re
import csv
import argparse
import xlsxwriter

# https://www.kaggle.com/ruchi798/movies-on-netflix-prime-video-hulu-and-disney


def print_separator():
    print("\n----------------------------------")


class Movie:
    def __init__(self, title, year, genres, platform, rate):
        self.title = title
        self.genres = genres
        self.year = year
        self.platform = platform
        self.rate = rate
        if self.rate == "":
            self.rate = "unknown"

    def __str__(self):
        return self.title+", "+str(self.year)+", "+self.genres+", "+self.platform+", "+str(self.rate)


def average_rate(dataset):
    total = 0
    count = 0
    for x in dataset:
        if x.rate != "unknown":
            total += float(x.rate)
            count += 1
    return total/count


def count_by_platform(dataset, platform):
    count = 0
    for x in dataset:
        if x.platform == platform:
            count += 1
    return count


def check_platform(line):
    if line[7] != '0':
        return "Netflix"
    elif line[8] != '0':
        return "Hulu"
    elif line[9] != '0':
        return "Prime Video"
    elif line[10] != '0':
        return "Disney+"
    else:
        return "unknown"


def count_of_movies_by_year(dataset):
    data = dict()
    for x in dataset:
        if x.year in data:
            data[x.year] += 1
        else:
            data[x.year] = 1
    return dict(sorted(data.items()))


def read_dataset(filename):
    movies = []
    with open(filename, encoding="utf8") as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=",")
        line_count = 0
        for x in csv_reader:
            if line_count == 0:
                print("START")
            else:
                title = x[2]
                year = x[3]
                rate = x[5]
                platform = check_platform(x)
                genres = x[13]
                movies.append(Movie(title, year, genres, platform, rate))
            line_count += 1
        print("Lines processed:", line_count)
        return movies


def data_into_xslx(filename, data, years):
    workbook = xlsxwriter.Workbook(filename)
    worksheet = workbook.add_worksheet()
    row = 0
    col = 0
    for item in data:
        worksheet.write(row, col, item)
        worksheet.write(row, col+1, data[item])
        row += 1
    row += 3
    worksheet.write(row, col, "COUNT OF MOVIES BY YEARS")
    row += 2
    for year in years:
        worksheet.write(row, col, year)
        worksheet.write(row, col+1, years[year])
        row += 1
    workbook.close()


def run():

    parser = argparse.ArgumentParser(
        description="Program to analyze dataset and report analyzed data into xlsx")
    parser.add_argument("filename")
    parser.add_argument(
        "-o", "--option", help="Write data into xml")
    args = parser.parse_args()
    if args.filename:
        csvRegex = r'.*\.(csv)'
        filename = sys.argv[1]
        res = re.match(csvRegex, filename)
        if res is not None:
            dataset = read_dataset(filename)
            avg = average_rate(dataset)
            year = count_of_movies_by_year(dataset)
            netflix = count_by_platform(dataset, "Netflix")
            disney = count_by_platform(dataset, "Disney+")
            hulu = count_by_platform(dataset, "Hulu")
            primeVideos = count_by_platform(dataset, "Prime Video")
            if args.option:
                print("Write report into:", args.option)
                xlsx_data = dict()
                xlsx_data["TOTAL FILMS"] = len(dataset)
                xlsx_data["AVG RATE"] = avg
                xlsx_data["NETFLIX COUNT"] = netflix
                xlsx_data["HULU COUNT"] = hulu
                xlsx_data["PRIME VIDEOS COUNT"] = primeVideos
                xlsx_data["DISNEY COUNT"] = disney

                data_into_xslx(args.option, xlsx_data, year)
            else:
                print("AVG RATE:", avg)
                print("COUNT BY YEAR:", year)
                print("NETFLIX COUNT:", netflix)
                print("HULU COUNT:", hulu)
                print("PRIME VIDEO COUNT:", primeVideos)
                print("DISNEY COUNT:", disney)

        else:
            print("Wrong filename")
            exit()
    else:
        print("No arguments!")
        exit()


if __name__ == "__main__":
    run()
