import board as b
import dice as d
from DontPassStrategy import init_bet, point_established
#from PassStrategy import init_bet, point_established

###
# For the purposes of this simulation, we assume only the following bets & payouts
# pass or don't pass: pays 1:1
# pass odds: 2:1 on 4 or 10
#            3:2 on 5 or 9
#            6:5 on 6 or 8
# don't odds: opposite of above
# place: 9:5 on 4 or 10
#        7:5 on 5 or 9
#        7:6 on 6 or 8
###

POINTS = [4, 5, 6, 8, 9, 10]
ROLLS_PER_HOUR = 100
HOURS_PER_GAME = 3
NUM_GAMES = 10000
STARTING_BANK = 100

games = []

for game in range(0, NUM_GAMES):
    board = b.Board()
    dice = d.Dice()
    bank = STARTING_BANK

    i = 0
    for i in range(0, (ROLLS_PER_HOUR*HOURS_PER_GAME)+1):
        if bank <= 0:
            break

        roll = dice.roll()

        # button off, and no init bet
        if board.get_point() is None:
            if not board.has_init_bet():
                bank -= init_bet(board)

        winnings = board.process_roll(roll)

        bank += winnings

        #print 'Rolled: %d\tWon: %d\tBank: %d\n' % (roll, winnings, bank)

        # this is only true after the point is established
        if roll == board.get_point():
            bank -= point_established(board, roll)

    #print 'Rolled: %d out of %d' % (i, ROLLS_PER_HOUR*HOURS_PER_GAME)
    #print 'Bank: ' + str(bank)
    #print str(dice)

    games.append({'rolls': i, 'bank': bank if bank > 0 else 0})

win_count = 0
avg_bank = 0

for game in games:
    win_count += 1 if game['bank'] > STARTING_BANK else 0
    avg_bank += game['bank']

print 'Avg Bank: %.2f\n%% Win: %.1f' % (avg_bank/float(len(games)), win_count/float(len(games))*100)