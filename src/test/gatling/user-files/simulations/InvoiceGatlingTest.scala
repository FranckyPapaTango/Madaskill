import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Invoice entity.
 */
class InvoiceGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the Invoice entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all invoices")
            .get("/api/invoices")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new invoice")
            .post("/api/invoices")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":"SAMPLE_TEXT"
                , "object":"SAMPLE_TEXT"
                , "accountCountry":"SAMPLE_TEXT"
                , "accountName":"SAMPLE_TEXT"
                , "accountTaxIds":"SAMPLE_TEXT"
                , "amountDue":"0"
                , "amountPaid":"0"
                , "amountRemaining":"0"
                , "amountShipping":"0"
                , "application":"SAMPLE_TEXT"
                , "applicationFeeAmount":"0"
                , "attemptCount":"0"
                , "attempted":null
                , "autoAdvance":null
                , "billingReason":"SAMPLE_TEXT"
                , "charge":"SAMPLE_TEXT"
                , "collectionMethod":"SAMPLE_TEXT"
                , "created":"0"
                , "currency":"SAMPLE_TEXT"
                , "customFields":"SAMPLE_TEXT"
                , "customerStringValue":"SAMPLE_TEXT"
                , "customerAddress":"SAMPLE_TEXT"
                , "customerEmail":"SAMPLE_TEXT"
                , "customerName":"SAMPLE_TEXT"
                , "customerPhone":"SAMPLE_TEXT"
                , "customerShipping":"SAMPLE_TEXT"
                , "customerTaxExempt":"SAMPLE_TEXT"
                , "customerTaxIds":"SAMPLE_TEXT"
                , "defaultPaymentMethod":"SAMPLE_TEXT"
                , "defaultSource":"SAMPLE_TEXT"
                , "defaultTaxRates":"SAMPLE_TEXT"
                , "description":"SAMPLE_TEXT"
                , "discount":"SAMPLE_TEXT"
                , "discounts":"SAMPLE_TEXT"
                , "dueDate":"0"
                , "effectiveAt":"0"
                , "endingBalance":"0"
                , "footer":"SAMPLE_TEXT"
                , "fromInvoice":"SAMPLE_TEXT"
                , "hostedInvoiceUrl":"SAMPLE_TEXT"
                , "invoicePdf":"SAMPLE_TEXT"
                , "lastFinalizationError":"SAMPLE_TEXT"
                , "latestRevision":"SAMPLE_TEXT"
                , "livemode":null
                , "metadata":"SAMPLE_TEXT"
                , "nextPaymentAttempt":"0"
                , "number":"SAMPLE_TEXT"
                , "onBehalfOf":"SAMPLE_TEXT"
                , "paid":null
                , "paidOutOfBand":null
                , "paymentIntent":"SAMPLE_TEXT"
                , "paymentSettings":"SAMPLE_TEXT"
                , "periodEnd":"0"
                , "periodStart":"0"
                , "postPaymentCreditNotesAmount":"0"
                , "prePaymentCreditNotesAmount":"0"
                , "quote":"SAMPLE_TEXT"
                , "receiptNumber":"SAMPLE_TEXT"
                , "rendering":"SAMPLE_TEXT"
                , "renderingOptions":"SAMPLE_TEXT"
                , "shippingCost":"0"
                , "shippingDetails":"SAMPLE_TEXT"
                , "startingBalance":"0"
                , "statementDescriptor":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "statusTransitions":"SAMPLE_TEXT"
                , "subscription":"SAMPLE_TEXT"
                , "subscriptionDetails":"SAMPLE_TEXT"
                , "subtotal":"0"
                , "subtotalExcludingTax":"0"
                , "tax":"SAMPLE_TEXT"
                , "testClock":"SAMPLE_TEXT"
                , "total":"0"
                , "totalDiscountAmounts":"SAMPLE_TEXT"
                , "totalExcludingTax":"0"
                , "totalTaxAmounts":"SAMPLE_TEXT"
                , "transferData":"SAMPLE_TEXT"
                , "webhooksDeliveredAt":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_invoice_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created invoice")
                .get("${new_invoice_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created invoice")
            .delete("${new_invoice_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
