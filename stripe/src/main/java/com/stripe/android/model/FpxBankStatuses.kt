package com.stripe.android.model

import com.stripe.android.view.FpxBank
import org.json.JSONObject

internal class FpxBankStatuses private constructor(
    private val statuses: Map<String, Boolean>? = null
) {
    /**
     * Defaults to `true` if statuses aren't available.
     */
    @JvmSynthetic
    internal fun isOnline(bank: FpxBank): Boolean {
        return statuses?.get(bank.id) ?: true
    }

    internal companion object {
        @JvmSynthetic
        internal fun fromJson(json: JSONObject?): FpxBankStatuses {
            return if (json == null) {
                DEFAULT
            } else {
                val statuses = StripeJsonUtils.optMap(json, "parsed_bank_status")
                FpxBankStatuses(statuses as Map<String, Boolean>)
            }
        }

        @get:JvmSynthetic
        internal val DEFAULT = FpxBankStatuses()
    }
}
