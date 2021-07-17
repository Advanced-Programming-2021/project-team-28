package org.model.enums;

import org.model.TrapsDescription;

public enum TrapEffect {
    TRAP_HOLE,
    MIRROR_FORCE,
    MAGIC_CYLINDER,
    MIND_CRUSH,
    TORRENTIAL_TRIBUTE,
    TIME_SEAL,
    NEGATE_ATTACK,
    SOLEMN_WARNING,
    MAGIC_JAMMER,
    CALL_OF_THE_HAUNTED;
    public String description;

    static {
        TRAP_HOLE.description = TrapsDescription.trapHole;
                MIRROR_FORCE.description = TrapsDescription.mirrorForce;
                MAGIC_CYLINDER.description = TrapsDescription.magicCylinder;
                MIND_CRUSH.description = TrapsDescription.mindCrush;
                TORRENTIAL_TRIBUTE.description = TrapsDescription.torrentialTribute;
                TIME_SEAL.description = TrapsDescription.timeSeal;
                NEGATE_ATTACK.description = TrapsDescription.negateAttack;
                SOLEMN_WARNING.description = TrapsDescription.solemnWarning;
                MAGIC_JAMMER.description = TrapsDescription.magicJammer;
                CALL_OF_THE_HAUNTED.description = TrapsDescription.callOfTheHaunted;
    }
}
