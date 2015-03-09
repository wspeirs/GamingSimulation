def round_up(num, mul):
    return mul * (1 + (num - 1) // mul) if num % mul != 0 else num


class Board:
    MIN_BET = 5

    def __init__(self):
        self.point = None
        self.bets = dict()
        self.clear_bets()

    def __str__(self):
        ret = 'point: ' + str(self.point) + "\n"
        ret += "pass: %d\todds: %d\t" % (self.bets['pass'], self.bets['pass_odds'])
        ret += "don't: %d\todds: %d\n" % (self.bets['dont_pass'], self.bets['dont_pass_odds'])

        for p in [4, 5, 6, 8, 9, 10]:
            ret += 'place_' + str(p) + ": " + str(self.bets['place_' + str(p)]) + "\t"

        return ret

    def set_point(self, point):
        if point is not None and (point < 2 or point > 12):
            raise ValueError("Invalid point: " + str(point))

        if self.point is  not None:
            raise ValueError("Point already set to " + str(self.point))

        self.point = point

    def get_point(self):
        return self.point

    def get_bet(self, bet):
        if bet not in self.bets:
            return 0
        else:
            return self.bets[bet]

    def has_init_bet(self):
        if 'pass' in self.bets and self.bets['pass'] > 0:
            return True
        if 'dont_pass' in self.bets and self.bets['dont_pass'] > 0:
            return True

        return False

    # helper method
    def calc_and_clear(self, bet, mul):
        ret = self.bets[bet] * mul
        self.bets[bet] = 0
        return ret

    def clear_bets(self):
        self.bets['pass'] = self.bets['pass_odds'] = 0
        self.bets['dont_pass'] = self.bets['dont_pass_odds'] = 0

        for p in [4, 5, 6, 8, 9, 10]:
            self.bets['place_' + str(p)] = 0

    def process_roll(self, roll):
        winnings = 0

        #print self

        if self.point is None:  # button off
            if roll in [7, 11]:
                winnings += self.calc_and_clear('pass', 2)
                self.clear_bets()
            elif roll in [2, 3]:
                winnings += self.calc_and_clear('dont_pass', 2)
                self.clear_bets()
            elif roll == 12:
                winnings += self.calc_and_clear('pass', 0)
            else:
                self.point = roll  # set the point

        else:  # button on
            if roll in [4, 5, 6, 8, 9, 10]:  # check place bets
                if roll == 4:
                    winnings += self.bets['place_4'] * 9/5.0
                elif roll == 10:
                    winnings += self.bets['place_10'] * 9/5.0
                elif roll == 5:
                    winnings += self.bets['place_5'] * 7/5.0
                elif roll == 9:
                    winnings += self.bets['place_9'] * 7/5.0
                elif roll == 6:
                    winnings += self.bets['place_6'] * 7/6.0
                elif roll == 8:
                    winnings += self.bets['place_8'] * 7/6.0

            if roll == self.point:  # button on, hit the point
                winnings += self.calc_and_clear('pass', 2)

                if self.point in [4, 10]:
                    winnings += self.calc_and_clear('pass_odds', 3)  # 2x + x = 3x
                elif self.point in [5, 9]:
                    winnings += self.calc_and_clear('pass_odds', 5/2.0)  # 3x/2 + 2x/2 = 5x/2
                elif self.point in [6, 8]:
                    winnings += self.calc_and_clear('pass_odds', 11/5.0)

                self.clear_bets()
                self.point = None

            elif roll == 7:  # button on, rolled a 7
                winnings += self.calc_and_clear('dont_pass', 2)

                if self.point in [4, 10]:
                    winnings += self.calc_and_clear('dont_pass_odds', 3/2.0)
                elif self.point in [5, 9]:
                    winnings += self.calc_and_clear('dont_pass_odds', 5/3.0)
                elif self.point in [6, 8]:
                    winnings += self.calc_and_clear('dont_pass_odds', 11/6.0)

                self.clear_bets()
                self.point = None

        return winnings


#
# These are all the bets
#
    def bet_pass(self, units):
        self.bets['pass'] = self.MIN_BET * units
        return self.bets['pass']

    def bet_dont_pass(self, units):
        self.bets['dont_pass'] = self.MIN_BET * units
        return self.bets['dont_pass']

    def bet_pass_odds(self, units):
        if self.point in [4, 10]:
            self.bets['pass_odds'] = self.MIN_BET * units
        elif self.point in [5, 9]:
            self.bets['pass_odds'] = round_up(self.MIN_BET * units, 2)
        elif self.point in [6, 8]:
            self.bets['pass_odds'] = round_up(self.MIN_BET * units, 5)

        return self.bets['pass_odds']

    def bet_dont_pass_odds(self, units):
        if self.point in [4, 10]:
            self.bets['dont_pass_odds'] = round_up(self.MIN_BET * units, 2)
        elif self.point in [5, 9]:
            self.bets['dont_pass_odds'] = round_up(self.MIN_BET * units, 3)
        elif self.point in [6, 8]:
            self.bets['dont_pass_odds'] = round_up(self.MIN_BET * units, 6)

        return self.bets['dont_pass_odds']

    def bet_place(self, units, point):
        if point in [4, 10, 5, 9]:
            self.bets['place_' + str(point)] = round_up(self.MIN_BET * units, 5)
        elif point in [6, 8]:
            self.bets['place_' + str(point)] = round_up(self.MIN_BET * units, 6)
        else:
            raise ValueError("Invalid point: " + str(point))

        return self.bets['place_' + str(point)]


