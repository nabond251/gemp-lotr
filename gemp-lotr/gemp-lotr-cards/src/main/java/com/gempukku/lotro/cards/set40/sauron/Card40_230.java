package com.gempukku.lotro.cards.set40.sauron;

import com.gempukku.lotro.logic.cardtype.AbstractMinion;
import com.gempukku.lotro.logic.modifiers.condition.AndCondition;
import com.gempukku.lotro.logic.modifiers.condition.NotCondition;
import com.gempukku.lotro.logic.modifiers.condition.SpotCulturesCondition;
import com.gempukku.lotro.common.Culture;
import com.gempukku.lotro.common.Keyword;
import com.gempukku.lotro.common.Race;
import com.gempukku.lotro.common.Side;
import com.gempukku.lotro.filters.Filters;
import com.gempukku.lotro.game.PhysicalCard;
import com.gempukku.lotro.game.state.LotroGame;
import com.gempukku.lotro.logic.modifiers.Modifier;
import com.gempukku.lotro.logic.modifiers.SpotCondition;
import com.gempukku.lotro.logic.modifiers.StrengthModifier;

import java.util.Collections;
import java.util.List;

/**
 * Title: Orc Ravager
 * Set: Second Edition
 * Side: Shadow
 * Culture: Sauron
 * Twilight Cost: 3
 * Type: Minion - Orc
 * Strength: 9
 * Vitality: 3
 * Home: 6
 * Card Number: 1U230
 * Game Text: Tracker. While you can spot another [SAURON] Orc and cannot spot more than 2 Free Peoples cultures,
 * each character skirmishing this minion is strength -1.
 */
public class Card40_230 extends AbstractMinion {
    public Card40_230() {
        super(3, 9, 3, 6, Race.ORC, Culture.SAURON, "Orc Ravager");
        addKeyword(Keyword.TRACKER);
    }

    @Override
    public List<? extends Modifier> getAlwaysOnModifiers(LotroGame game, PhysicalCard self) {
        StrengthModifier modifier = new StrengthModifier(self, Filters.inSkirmishAgainst(self),
                new AndCondition(
                        new SpotCondition(Culture.SAURON, Race.ORC, Filters.not(self)),
                        new NotCondition(new SpotCulturesCondition(3, Side.FREE_PEOPLE))), -1);
        return Collections.singletonList(modifier);
    }
}