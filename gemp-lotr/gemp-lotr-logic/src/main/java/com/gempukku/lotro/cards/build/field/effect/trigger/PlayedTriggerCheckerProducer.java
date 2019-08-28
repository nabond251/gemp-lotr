package com.gempukku.lotro.cards.build.field.effect.trigger;

import com.gempukku.lotro.cards.build.CardGenerationEnvironment;
import com.gempukku.lotro.cards.build.FilterableSource;
import com.gempukku.lotro.cards.build.InvalidCardDefinitionException;
import com.gempukku.lotro.cards.build.field.FieldUtils;
import com.gempukku.lotro.common.Filterable;
import com.gempukku.lotro.game.PhysicalCard;
import com.gempukku.lotro.game.state.LotroGame;
import com.gempukku.lotro.logic.timing.Effect;
import com.gempukku.lotro.logic.timing.EffectResult;
import com.gempukku.lotro.logic.timing.TriggerConditions;
import org.json.simple.JSONObject;

public class PlayedTriggerCheckerProducer implements TriggerCheckerProducer {
    @Override
    public TriggerChecker getTriggerChecker(JSONObject value, CardGenerationEnvironment environment) throws InvalidCardDefinitionException {
        FieldUtils.validateAllowedFields(value, "filter");

        final String filterString = FieldUtils.getString(value.get("filter"), "filter");
        final FilterableSource filter = environment.getFilterFactory().generateFilter(filterString);
        return new TriggerChecker() {
            @Override
            public boolean accepts(String playerId, LotroGame game, PhysicalCard self, EffectResult effectResult, Effect effect) {
                final Filterable filterable = filter.getFilterable(playerId, game, self, effectResult, effect);
                return TriggerConditions.played(game, effectResult, filterable);
            }

            @Override
            public boolean isBefore() {
                return false;
            }
        };
    }
}