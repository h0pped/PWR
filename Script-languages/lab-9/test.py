import math


class Person:
    def __init__(self, id):
        self.id = id


obama = Person(100)
obama.__dict__['age'] = 49


class parent:
    def __init__(self, param):
        self.v1 = param


class child(parent):
    def __init__(self, param):
        self.v2 = param


foo = {}
print(len(foo))


def run():
    print((80-12*5)/4)


if __name__ == "__main__":
    run()
