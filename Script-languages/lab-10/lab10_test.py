import pytest

import lab10
from lab10 import HTTPVersionException, TypeException, HttpRequest


def test1():
    with pytest.raises(TypeError):
        lab10.reqstr2obj(2222)
    with pytest.raises(TypeError):
        lab10.reqstr2obj([])


def test2():
    obj = lab10.reqstr2obj("GET / HTTP1.1")
    assert type(obj) == HttpRequest


def test3():
    obj = lab10.reqstr2obj("GET / HTTP1.1")
    assert obj.rtype == "GET" and obj.path == "/" and obj.protocol == "HTTP1.1"


@pytest.mark.parametrize("rtype, path, protocol", [("UPDATE", "/some", "HTTP1.0"), ("POST", "/products", "HTTP2.0"), ("GET", "/something", "HTTP1.1")])
def test4(rtype, path, protocol):
    obj = lab10.reqstr2obj(f"{rtype} {path} {protocol}")
    assert obj.rtype == rtype and obj.path == path and obj.protocol == protocol


@pytest.mark.parametrize("request_str",
                         ["PATCH /    ", "GET HTTP2.0", " . . .", "POST /    HTTP1.0"])
def test5(request_str):
    obj = lab10.reqstr2obj(request_str)
    assert obj is None


@pytest.mark.parametrize("request_str",
                         ["DOWNLOAD / HTTP1.1", "UPLOAD /admin HTTP1.1", "FAKE /something HTTP1.1"])
def test6(request_str):
    with pytest.raises(TypeException):
        lab10.reqstr2obj(request_str)


@pytest.mark.parametrize("request_str",
                         ["PATCH / HTTP0.9", "GET /admin HTTP2.2", "UPDATE /something HTTP3.93"])
def test7(request_str):
    with pytest.raises(HTTPVersionException):
        lab10.reqstr2obj(request_str)


@pytest.mark.parametrize("request_str",
                         ["PATCH s/ HTTP0.9", "GET d/admin HTTP2.2", "UPDATE ./something HTTP3.93"])
def test8(request_str):
    with pytest.raises(HTTPVersionException):
        lab10.reqstr2obj(request_str)
