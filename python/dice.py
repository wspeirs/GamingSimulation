import random


class Dice:
    def __init__(self):
        self.rolls = []
        self.histo = dict()

        for i in range(2, 13):
            self.histo[i] = 0

    def __str__(self):
        r_count = float(len(self.rolls))
        ret = ''
        for i in range(2, 13):
            ret += "%2d: %2.2f\n" % (i, self.histo[i]/r_count*100)

        return ret

    def roll(self):
        d1 = random.randint(1, 6)
        d2 = random.randint(1, 6)

        self.rolls.append([d1, d2])
        self.histo[d1 + d2] += 1

        return d1 + d2