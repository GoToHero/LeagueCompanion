package ru.go2hero.leaguecompanion.data

data class SummonerProfile (
    //ID of the summoner icon associated with the summoner.
    val profileIconId: Int?,
    //Summoner name.
    val name: String?,
    //Encrypted PUUID. Exact length of 78 characters.
    val puuid: String?,
    //Summoner level associated with the summoner.
    val summonerLevel: Long?,
    //Summoner last activity
    val revisionDate: Long?,
    //Encrypted summoner ID. Max length 63 characters.
    val id: String?,
    //Encrypted account ID. Max length 56 characters.
    val accountId: String?
)