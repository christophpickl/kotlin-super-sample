package kss.server.app

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

fun applicationKodein(konfig: Konfig) = Kodein {
    bind<Konfig>() with instance(konfig)
}
