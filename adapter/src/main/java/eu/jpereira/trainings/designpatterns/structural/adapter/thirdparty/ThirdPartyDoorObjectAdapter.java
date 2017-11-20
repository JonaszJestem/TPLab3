package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

/**
 * Created by Jonasz on 2017-10-26.
 */
public class ThirdPartyDoorObjectAdapter implements Door {
    private ThirdPartyDoor door = new ThirdPartyDoor();

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            door.unlock(code);
            door.setState(ThirdPartyDoor.DoorState.OPEN);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            cannotChangeStateOfLockedDoor.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            door.setState(ThirdPartyDoor.DoorState.CLOSED);
            door.lock();
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            cannotChangeStateOfLockedDoor.printStackTrace();
        }
    }

    @Override
    public boolean isOpen() {
        return door.getLockStatus().equals(ThirdPartyDoor.LockStatus.UNLOCKED);
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(door.getLockStatus().equals(ThirdPartyDoor.LockStatus.LOCKED)) {
            try {
                door.unlock(oldCode);
            } catch (CannotUnlockDoorException e) {
                throw new IncorrectDoorCodeException();
            }
        }
        if(newCode.equals(newCodeConfirmation)) {
            try {
                door.setNewLockCode(newCode);
            } catch (CannotChangeCodeForUnlockedDoor cannotChangeCodeForUnlockedDoor) {
                cannotChangeCodeForUnlockedDoor.printStackTrace();
            }
        } else {
            throw new CodeMismatchException();
        }
    }

    @Override
    public boolean testCode(String code) {
        boolean shouldLockAfter = door.getLockStatus() == ThirdPartyDoor.LockStatus.LOCKED ? true : false;
        boolean codeIsCorrect = false;
        try {
            door.unlock(code);
            if(door.getLockStatus() == ThirdPartyDoor.LockStatus.UNLOCKED) {
                codeIsCorrect = true;
            }
        } catch (CannotUnlockDoorException e) {
            codeIsCorrect = false;
        }
        finally {
            if(shouldLockAfter) door.lock();
            return codeIsCorrect;
        }
    }
}
