package com.openrun.wantrunning.core.model

data class RunningHistory(
    val date: String,
    val isParty: Boolean,
    val time: String,
    val distance: Int,
    val heartbeat: Int
) {
    companion object {
        val recentMockingDataList = listOf(
            RunningHistory(date = "2023.02.13", isParty = false, time = "0:11:11", distance = 1, heartbeat = 165),
            RunningHistory(date = "2023.02.13", isParty = false, time = "0:22:22", distance = 2, heartbeat = 166),
            RunningHistory(date = "2023.02.12", isParty = true, time = "0:33:33", distance = 3, heartbeat = 167),
            RunningHistory(date = "2023.02.11", isParty = true, time = "0:44:44", distance = 4, heartbeat = 168),
            RunningHistory(date = "2023.02.10", isParty = false, time = "0:10:10", distance = 5, heartbeat = 166),
            RunningHistory(date = "2023.02.10", isParty = true, time = "0:30:00", distance = 3, heartbeat = 155)
        )

        val selectedMockingData = listOf(
            RunningHistory(date = "2023.02.13", isParty = true, time = "0:10:10", distance = 1, heartbeat = 160),
            RunningHistory(date = "2023.02.13", isParty = false, time = "0:11:11", distance = 2, heartbeat = 161)
        )
    }
}
