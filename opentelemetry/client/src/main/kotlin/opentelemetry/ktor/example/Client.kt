package opentelemetry.ktor.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.websocket.*
import opentelemetry.ktor.example.plugins.opentelemetry.installOpenTelemetryOnClient

suspend fun main() {
    val openTelemetry = getOpenTelemetry(serviceName = "opentelemetry-ktor-sample-client")

    val client = HttpClient(CIO) {
        install(WebSockets)

        defaultRequest {
            url("http://$SERVER_HOST:$SERVER_PORT")
        }

        installOpenTelemetryOnClient(openTelemetry)
    }

    doRequests(client)
}
