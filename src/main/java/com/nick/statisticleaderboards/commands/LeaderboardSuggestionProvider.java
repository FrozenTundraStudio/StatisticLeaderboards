package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LeaderboardSuggestionProvider implements SuggestionProvider<CommandSourceStack> {
    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        List<String> suggestions = new ArrayList<>();
        suggestions.add("walked");
        suggestions.add("damagedealt");
        suggestions.add("damagetaken");
        suggestions.add("jumps");
        suggestions.add("mobkills");
        suggestions.add("deaths");

        for (String suggestion : suggestions) {
            builder.suggest(suggestion);
        }
        return builder.buildFuture();
    }
}
