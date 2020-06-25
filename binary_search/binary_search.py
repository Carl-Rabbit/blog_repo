def bs_base(nums, target):
    """
    从元素各不相同的nums中找到target所在的下标。
    如果没有找到，则返回-index-1，表示target应该插入index前。
    """
    lo, hi = 0, len(nums) - 1

    while lo <= hi:
        mi = (lo + hi) // 2
        if nums[mi] < target:
            lo = mi + 1
        elif nums[mi] > target:
            hi = mi - 1
        else:
            return mi

    return - lo - 1


def bs_first(nums, condition):
    """
    从nums中找到符合条件的、最靠前的元素所在下标。
    如果没有任何一个元素符合条件，返回-1
    """
    lo, hi = 0, len(nums) - 1

    while lo <= hi:
        mi = (lo + hi) // 2
        if not condition(nums[mi]):
            lo = mi + 1
        else:
            hi = mi - 1

    # 此时lo == hi + 1
    if lo >= len(nums):
        return -1

    return lo


def bs_last(nums, condition):
    """
    从nums中找到符合条件的、最靠后的元素所在下标。
    如果没有任何一个元素符合条件，返回-1
    """
    lo, hi = 0, len(nums) - 1

    while lo <= hi:
        mi = (lo + hi) // 2
        if condition(nums[mi]):  # 翻转条件
            lo = mi + 1
        else:
            hi = mi - 1

    # 没有额外判断的原因是，Miss时hi本来就为-1

    return hi  # lo -> hi


def bs_first_num(nums, target):
    """
    从nums中找到第一个target出现位置的下标。
    如果target没有出现，返回-1。
    """
    idx = bs_first(nums, lambda a: a >= target)
    return idx if nums[idx] == target else -1


"""
def bs_first_num(nums, target):
    \"""
    从nums中找到第一个target出现位置的下标。
    如果target没有出现，返回-1。
    \"""
    lo, hi = 0, len(nums) - 1

    while lo <= hi:
        mi = (lo + hi) // 2
        if not nums[mi] >= target:
            lo = mi + 1
        else:
            hi = mi - 1

    # 仅在模板bs_first上改变这里即可
    if lo >= len(nums) or nums[lo] != target:
        return -1

    return lo
"""


def bs_range(nums, target):
    """
    从nums中找到target出现的区段
    如果target没有出现，返回[-1, -1]
    """
    lower = bs_first(nums, lambda a: a >= target)
    if lower == -1 or nums[lower] != target:
        return [-1, -1]
    upper = bs_first(nums, lambda a: a > target)
    return [lower, upper - 1 if upper != -1 else len(nums) - 1]
