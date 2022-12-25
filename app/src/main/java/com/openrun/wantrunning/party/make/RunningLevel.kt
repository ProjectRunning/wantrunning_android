package com.openrun.wantrunning.party.make

enum class RunningLevel(val displayedValue: String) {
    BEGINNER("초심자"),
    INTERMEDIATE("중수"),
    ADVANCED("고수");

    companion object {
        fun displayedValues(): List<String> = values().map { it.displayedValue }

        fun findValueByDisplayedValue(displayedValue: String): RunningLevel? =
            values().find { it.displayedValue == displayedValue }
    }
}
