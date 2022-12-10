package com.openrun.wantrunning.party.make

enum class RunningPace(val displayedValue: String) {
    UNKNOWN("잘 몰라요"),
    OVER_9("9분 이상"),
    OVER_7_UNDER_9("7~9분"),
    OVER_6_UNDER_7("6~7분"),
    OVER_4_UNDER_5("4~5분"),
    UNDER_4("4분 이하");

    companion object {
        fun displayedValues(): List<String> = values().map { it.displayedValue }

        fun findValueByDisplayedValue(displayedValue: String): RunningPace? =
            RunningPace.values().find { it.displayedValue == displayedValue }
    }
}