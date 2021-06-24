
class TypeException(Exception):
    pass


class HTTPVersionException(Exception):
    pass

# 1


class HttpRequest:
    def __init__(self, rtype, path, protocol):
        self.rtype = rtype
        self.path = path
        self.protocol = protocol
# 2


def reqstr2obj(request_string):
    if type(request_string) == str:
        request_data = request_string.split(" ")  # separated by space
        if len(request_data) != 3:
            return None
        rtype = request_data[0]
        path = request_data[1]
        protocol = request_data[2]

        if rtype not in ["GET", "POST", "PUT", "PATCH", "DELETE", "HEAD", "TRACE", "OPTIONS", "CONNECT", "UPDATE"]:
            raise TypeException
        if protocol not in ["HTTP1.0", "HTTP1.1", "HTTP2.0"]:
            raise HTTPVersionException
        if not path.startswith("/"):
            raise ValueError("Path must start with /")

        return HttpRequest(rtype, path, protocol)
    else:
        raise TypeError


def run():
    x = HttpRequest("GET", "/hello", "HTTP 1.1")
    print(x.rtype)


if __name__ == "__main__":
    run()
