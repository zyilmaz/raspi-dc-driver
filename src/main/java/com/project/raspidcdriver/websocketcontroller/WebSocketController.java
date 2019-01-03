package com.project.raspidcdriver.websocketcontroller;

import com.project.raspidcdriver.raspberry.MoveController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private static MoveController moveController = new MoveController();

    @MessageMapping("/move/forward")
    public void moveForward() {
        moveController.forward();
        System.out.println("Moving forward...");
    }

    @MessageMapping("/move/backward")
    public void moveBackward() {
        moveController.backward();
        System.out.println("Moving backward...");
    }

    @MessageMapping("/move/right")
    public void moveRight() {
        moveController.right();
        System.out.println("Moving right...");
    }

    @MessageMapping("/move/left")
    public void moveLeft() {
        moveController.left();
        System.out.println("Moving left...");
    }

    @MessageMapping("/move/stop")
    public void moveStop() {
        moveController.stop();
        System.out.println("Stopping Moving...");
    }

}
