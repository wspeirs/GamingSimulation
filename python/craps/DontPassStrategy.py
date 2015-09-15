
def init_bet(board):
    return board.bet_dont_pass(1)


def point_established(board, roll):
    bets = 0

    return bets

    #if roll in [6, 8]:
    #    bets += board.bet_dont_pass_odds(1)

        #bets += board.bet_place(1, 6)
        #bets += board.bet_place(1, 8)

    if roll in [5, 9]:
        bets += board.bet_dont_pass_odds(1)

    elif roll in [4, 10]:
        bets += board.bet_dont_pass_odds(1)

    return bets
