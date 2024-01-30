package strategies;

import models.SlotAssignmentStrategyType;

public class SlotAssignmentStrategyFactory {

    public static SlotAssignmentStrategy getSloAssignmentStrategyByType(SlotAssignmentStrategyType slotAssignmentStrategyType){
        return  new RandomSlotAssignmentStrategy();
    }
}
