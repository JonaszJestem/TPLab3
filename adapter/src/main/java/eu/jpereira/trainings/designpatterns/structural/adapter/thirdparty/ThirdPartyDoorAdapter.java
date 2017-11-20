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
public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            this.unlock(code);
            this.setState(DoorState.OPEN);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            cannotChangeStateOfLockedDoor.printStackTrace();
        }
    }

    @Override
    public void close() {
        this.lock();
        try {
            this.setState(DoorState.OPEN);
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            cannotChangeStateOfLockedDoor.printStackTrace();
        }
    }

    @Override
    public boolean isOpen() {
        return this.getLockStatus().equals(LockStatus.UNLOCKED);
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        try {
                this.unlock(oldCode);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }

        if(newCode.equals(newCodeConfirmation)) {
            try {
                this.setNewLockCode(newCode);
            } catch (CannotChangeCodeForUnlockedDoor cannotChangeCodeForUnlockedDoor) {
                cannotChangeCodeForUnlockedDoor.printStackTrace();
            }
        } else {
            throw new CodeMismatchException();
        }
    }

    @Override
    public boolean testCode(String code) {
        boolean shouldLockAfter = this.getLockStatus().equals(ThirdPartyDoor.LockStatus.LOCKED);
        boolean codeIsCorrect = false;
        try {
            this.unlock(code);
            if(this.getLockStatus() == ThirdPartyDoor.LockStatus.UNLOCKED) {
                codeIsCorrect = true;
            }
        } catch (CannotUnlockDoorException e) {
            codeIsCorrect = false;
        }
        finally {
            if(shouldLockAfter) this.lock();
            return codeIsCorrect;
        }
    }
}
