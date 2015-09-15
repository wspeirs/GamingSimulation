from __future__ import print_function
from random import randrange

REDS = [1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36]
BLACKS = [2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35]
GREENS = [0, 00]
ALL = REDS + BLACKS + GREENS

# set these
START_AMT = 5121
MIN_BET = 5
MAX_BET = 3000
ROI_PERCENT = .10

WIN_AMT = START_AMT * (1 + ROI_PERCENT)

fd = open('log.txt', 'w')

print("MIN BET\tMAX BET\tSTART AMT\tWIN_AMT", file=fd)
print("%d\t%d\t%d\t%d" % (MIN_BET, MAX_BET, START_AMT, WIN_AMT), file=fd)

print("\t\tRoll\tColor\tResult\tBet Amnt\tWin\tBalance", file=fd)
print("0\t-\t-\t-\t-\t0\t" + str(START_AMT), file=fd)


def num_to_color(num):
    if num in REDS:
        return 'RED'
    elif num in BLACKS:
        return 'BLACK'
    elif num in GREENS:
        return 'GREEN'
    else:
        raise ValueError("Unknown number: " + str(num))


def get_bet():
    return REDS[0]


def get_bet_amt(_last_bet, _won_last):
    if _won_last:
        return MIN_BET
    else:
        return max(2 * _last_bet, MIN_BET)

TOTAL_GAMES = 10000
games_won = 0
avg_won = 0
games_lost = 0
avg_lost = 0

for game_num in range(10000):
    last_bet_amnt = 0
    won_last = False
    roll_count = 1
    balance = START_AMT

    while True:
        roll = ALL[randrange(len(ALL))]
        color = num_to_color(roll)

        bet_color = num_to_color(get_bet())
        last_bet_amnt = get_bet_amt(last_bet_amnt, won_last)

        if color == bet_color:
            won_last = True
            won = last_bet_amnt
        else:
            won_last = False
            won = -1 * last_bet_amnt

        balance += won

        print("%d\t%d\t%s\t%s\t%d\t%d\t%d" % (roll_count, roll, color, 'WON' if won_last else 'LOST', last_bet_amnt, won, balance), file=fd)

        if last_bet_amnt > MAX_BET or balance < 0:
            print(str(game_num+1) + "\tBUSTED!", file=fd)
            avg_lost += roll_count
            games_lost += 1
            break

        if balance >= WIN_AMT:
            print(str(game_num+1) + "\tREACHED ROI", file=fd)
            avg_won += roll_count
            games_won += 1
            break

        roll_count += 1


print("Games won:\t%d in avg of %d rolls" % (games_won, avg_won/games_won))
print("Games lost:\t%d in avg of %d rolls" % (games_lost, avg_lost/games_lost))