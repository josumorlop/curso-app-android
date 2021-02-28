package es.crmone.app.presentation.home

//INCOMING_TYPE, OUTGOING_TYPE, MISSED_TYPE, VOICEMAIL_TYPE
enum class CallType {
    incoming,
    outgoing,
    missed,
    voicemail,
    rejected,
    unknown;
    fun create(cod: Int): CallType {
        return when (cod) {
            1 -> incoming
            2 -> outgoing
            3 -> missed
            4 -> voicemail
            5 -> rejected
            else -> unknown
        }
    }
}
class CrmCall(val name: String, val telephone: String, val type: CallType, val date: String)