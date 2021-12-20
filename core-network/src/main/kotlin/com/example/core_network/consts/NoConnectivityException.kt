package com.example.core_network.consts

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "oh no you suck !"
}
