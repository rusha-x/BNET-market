package io.rusha.bnet_market.ui.home

class GetEntriesResponse(val data: List<List<App>>) {
    fun entryList(): List<App> {
        return data.firstOrNull() ?: emptyList()
    }
}