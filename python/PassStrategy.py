
def init_bet(board):
    return board.bet_pass(1)


def point_established(board, roll):
    bets = 0

    return bets

    if roll == 6:
        bets += board.bet_pass_odds(2)
        bets += board.bet_place(1, 8)

    elif roll == 8:
        bets += board.bet_pass_odds(2)
        bets += board.bet_place(1, 6)

    elif roll in [5, 9]:
        bets += board.bet_pass_odds(1)

    return bets
