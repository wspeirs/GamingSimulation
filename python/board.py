
class Board:
    def __init__(self):
        self.point = None

    def set_point(self, point):
        if point < 2 or point > 12:
            raise ValueError("Invalid point: " + point)

        if self.point is  not None:
            raise ValueError("Point already set to " + self.point)

        self.point = point

    def get_point(self):
        return self.point