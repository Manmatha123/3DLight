package com.project3.Game.GameObject;

import com.project3.Input.Input;

public interface IControlled {
    void updateMovement();
    void move(float amount);
    void strafe(float amount);
}
