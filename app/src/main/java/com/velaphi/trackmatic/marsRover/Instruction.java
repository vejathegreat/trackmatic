package com.velaphi.trackmatic.marsRover;

public enum Instruction {
    L, R, M;

    public void execute(RoverPosition position) {
        switch (this) {
            case L:
                position.setDirectionLeft();
                break;
            case R:
                position.setDirectionRight();
                break;
            case M:
                switch (position.getDirection()) {
                    case N:
                        position.setY(position.getY() + 1);
                        break;
                    case E:
                        position.setX(position.getX() + 1);
                        break;
                    case W:
                        position.setX(position.getX() - 1);
                        break;
                    case S:
                        position.setY(position.getY() - 1);
                        break;
                }
                break;
        }
    }
}
