# Kotlin Super Sample

A full fledged microservice sample along with some client, everything written in Kotlin 🙌🏻

## Tech Stack Server Side

- [Ktor](https://ktor.io/) ... Web Service
- [Kodein](https://kodein.org/) ... Dependency Injection
- [Exposed](https://github.com/JetBrains/Exposed) ... Persistence Layer
- kotlinx.serialization
- OpenAPI/Swagger? Any alternative to it?

## Design, Architecture

- Real ReST! (hypermedia, links, maturity level)
- Event Sourcing, CQRS
- Ports and Adapters / Hexagonal Architecture
- DDD ... Domain Driven Development
- BDD ... Behavioural Driven Development

## Build, Deploy, Run

- [Travis CI](https://travis-ci.org) ... Opensource cloud build
- Docker
- [AWS](https://aws.amazon.com/) ... Online cloud hosting
- KScript
- Coverage (opensource)
- dependency version check (versioneye)
- Todo plugin
- Detekt ... Static code analysis


## Clients

1. HTML/Kotlin DSL
1. Kotlin/JS
1. Kotlin/Android
1. Kotlin/Native (iOS)
1. [TornadoFx](https://tornadofx.io/) ... Desktop Client
