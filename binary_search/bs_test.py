from binary_search import *


def test_bs_base():
    test_data = [
        [[[1, 2, 3, 4, 6, 7, 8, 9], 6], 4],
        [[[1, 2, 3, 4, 6, 7, 8, 9], 0], -1],
        [[[1, 2, 3, 4, 6, 7, 8, 9], 5], -5],
        [[[1, 2, 3, 4, 6, 7, 8, 9], 10], -9],
    ]
    print('test: bs_base')
    for data, ans in test_data:
        actual = bs_base(data[0], data[1])
        if actual == ans:
            print('.', end='')
        else:
            print('F')
            print('data:', data)
            print('expe:', ans)
            print('actu:', actual)
    print()


def test_bs_first():
    test_data = [
        [[[1, 2, 3, 4, 5, 6], lambda a: a >= 4], 3],
        [[[1, 2, 3, 4, 5, 6], lambda a: a > 4], 4],
        [[[1, 2, 3, 3.5, 5, 6], lambda a: a > 4], 4],
        [[[1, 2, 3, 4, 5, 6], lambda a: a >= -1], 0],
        [[[1, 2, 3, 4, 5, 6], lambda a: a >= 1], 0],
        [[[1, 2, 3, 4, 5, 6], lambda a: a >= 6], 5],
        [[[1, 2, 3, 4, 5, 6], lambda a: a > 6], -1],
    ]
    print('test: bs_first')
    for data, ans in test_data:
        actual = bs_first(data[0], data[1])
        if actual == ans:
            print('.', end='')
        else:
            print('F')
            print('data:', data)
            print('expe:', ans)
            print('actu:', actual)
    print()


def test_bs_last():
    test_data = [
        [[[1, 2, 3, 4, 5, 6], lambda a: a <= 4], 3],
        [[[1, 2, 3, 4, 5, 6], lambda a: a < 4], 2],
        [[[1, 2, 3, 4, 5, 6], lambda a: a <= -1], -1],
        [[[1, 2, 3, 4, 5, 6], lambda a: a <= 1], 0],
        [[[1, 2, 3, 4, 5, 6], lambda a: a <= 6], 5],
        [[[1, 2, 3, 4, 5, 6], lambda a: a < 6], 4],
        [[[1, 2, 3, 4, 5, 6], lambda a: a < 7], 5],
    ]
    print('test: bs_last')
    for data, ans in test_data:
        actual = bs_last(data[0], data[1])
        if actual == ans:
            print('.', end='')
        else:
            print('F')
            print('data:', data)
            print('expe:', ans)
            print('actu:', actual)
    print()


def test_bs_first_num():
    test_data = [
        [[[1, 1, 1, 2, 2, 2, 4, 4], 2], 3],
        [[[1, 1, 1, 2, 2, 2, 4, 4], 0], -1],
        [[[1, 1, 1, 2, 2, 2, 4, 4], 1], 0],
        [[[1, 1, 1, 2, 2, 2, 4, 4], 3], -1],
        [[[1, 1, 1, 2, 2, 2, 4, 4], 4], 6],
    ]
    print('test: bs_first_num')
    for data, ans in test_data:
        actual = bs_first_num(data[0], data[1])
        if actual == ans:
            print('.', end='')
        else:
            print('F')
            print('data:', data)
            print('expe:', ans)
            print('actu:', actual)
    print()


def test_bs_range():
    test_data = [
        [[[1], 1], [0, 0]],
        [[[1], 2], [-1, -1]],
        [[[1, 1, 2], 1], [0, 1]],
        [[[1, 2, 2], 2], [1, 2]],
        [[[3, 3, 3], 3], [0, 2]],
        [[[5, 7, 7, 8, 8, 10], 8], [3, 4]],
        [[[5, 7, 7, 8, 8, 10], 6], [-1, -1]],
    ]
    print('test: bs_range')
    for data, ans in test_data:
        actual = bs_range(data[0], data[1])
        if actual == ans:
            print('.', end='')
        else:
            print('F')
            print('data:', data)
            print('expe:', ans)
            print('actu:', actual)
    print()


if __name__ == '__main__':
    test_bs_base()
    test_bs_first()
    test_bs_last()
    test_bs_first_num()
    test_bs_range()
