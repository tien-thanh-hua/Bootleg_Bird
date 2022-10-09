package com.bootleg.game.utils;

import com.bootleg.game.actors.Bird;
import com.bootleg.game.actors.Land;
import com.bootleg.game.actors.Pipe;

public class ScrollHandler {

    // ScrollHandler will create all five objects that we need.
    private Land _land1, _land2;
    private Pipe _pipe1, _pipe2, _pipe3;

    public static final float SCROLL_SPEED = -59;
    public static final float PIPE_GAP = Config.pipeGap;

    // tells where to create Land and Pipe objects
    public ScrollHandler(float yPos) {
        _land1 = new Land(0, yPos, 143, 11, SCROLL_SPEED);
        _land2 = new Land(_land1.getTailX(), yPos, 143, 11, SCROLL_SPEED);

        _pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
        _pipe2 = new Pipe(_pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
        _pipe3 = new Pipe(_pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
    }

    public Land getLand1() {
        return _land1;
    }
    public Land getLand2() {
        return _land2;
    }

    public Pipe getPipe1() {
        return _pipe1;
    }
    public Pipe getPipe2() {
        return _pipe2;
    }
    public Pipe getPipe3() {
        return _pipe3;
    }

    public void update(float delta) {
        // Update our objects
        _land1.update(delta);
        _land2.update(delta);
        _pipe1.update(delta);
        _pipe2.update(delta);
        _pipe3.update(delta);

        // if any pipe is scrolled left, reset
        if (_pipe1.isScrolledLeft()) {
            _pipe1.reset(_pipe3.getTailX() + PIPE_GAP);
        } else if (_pipe2.isScrolledLeft()) {
            _pipe2.reset(_pipe1.getTailX() + PIPE_GAP);
        } else if (_pipe3.isScrolledLeft()) {
            _pipe3.reset(_pipe2.getTailX() + PIPE_GAP);
        }

        // if any piece of land is scrolled left, reset
        if (_land1.isScrolledLeft()) {
            _land1.reset(_land2.getTailX());
        } else if (_land2.isScrolledLeft()) {
            _land2.reset(_land1.getTailX());
        }
    }

    public boolean collides(Bird bird) {
        return (_pipe1.collides(bird) ||
                (_pipe2.collides(bird)) ||
                (_pipe3.collides(bird)) ||
                (_land1.collides(bird) ||
                (_land2.collides(bird))));
    }

    public void stop() {
        _land1.stop();
        _land2.stop();
        _pipe1.stop();
        _pipe2.stop();
        _pipe3.stop();
    }
}
