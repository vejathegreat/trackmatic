package com.velaphi.trackmatic.marsRover;

public enum Direction {
    N {
        @Override
        public Direction left() {
            return W;
        }

        @Override
        public Direction right() {
            return E;
        }
    },
    E {
        @Override
        public Direction left() {
            return N;
        }

        @Override
        public Direction right() {
            return S;
        }
    },
    W {
        @Override
        public Direction left() {
            return S;
        }

        @Override
        public Direction right() {
            return N;
        }
    },
    S {
        @Override
        public Direction left() {
            return E;
        }

        @Override
        public Direction right() {
            return W;
        }
    };

    public abstract Direction left();

    public abstract Direction right();
}
