package com.gempukku.lotro.logic.modifiers.cost;

import com.gempukku.lotro.common.Filterable;
import com.gempukku.lotro.filters.Filters;
import com.gempukku.lotro.game.PhysicalCard;
import com.gempukku.lotro.game.state.LotroGame;
import com.gempukku.lotro.logic.actions.AbstractCostToEffectAction;
import com.gempukku.lotro.logic.effects.AddThreatsEffect;
import com.gempukku.lotro.logic.modifiers.AbstractExtraPlayCostModifier;
import com.gempukku.lotro.logic.modifiers.Condition;
import com.gempukku.lotro.logic.timing.Action;
import com.gempukku.lotro.logic.timing.Effect;
import com.gempukku.lotro.logic.timing.PlayConditions;

import java.util.Collections;
import java.util.List;

public class AddThreatExtraPlayCostModifier extends AbstractExtraPlayCostModifier {
    private int count;

    public AddThreatExtraPlayCostModifier(PhysicalCard source, int count, Condition condition, Filterable ...affects) {
        super(source, "Add "+count+" threat(s) to play", Filters.and(affects), condition);
        this.count = count;
    }

    @Override
    public boolean canPayExtraCostsToPlay(LotroGame game, PhysicalCard card) {
        return PlayConditions.canAddThreat(game, card, count);
    }

    @Override
    public void appendExtraCosts(LotroGame game, AbstractCostToEffectAction action, PhysicalCard card) {
        action.appendCost(
                new AddThreatsEffect(card.getOwner(), card, count));
    }
}