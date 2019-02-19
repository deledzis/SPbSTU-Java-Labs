package ru.deledzis.spbstu.java.labs.utils

import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.logging.Level
import java.util.logging.Logger

/**
 * An auxiliary function to log any message with any level of importance and print this message to user
 *
 * @param message is a String that will be logged with [logLevel] and printed to user's console
 * @param logLevel level of importance of log message
 * @param exitCode is an error code that will be used to exit the application
 */
private fun logAny(message: String, logLevel: Level, exitCode: Int = 0) {
    Logger.getGlobal().log(logLevel, message)

    if (logLevel != Level.INFO) {
        println(message)
    }

    if (exitCode != 0) {
        System.exit(exitCode)
    }
}

/**
 * An auxiliary function to log fine message and print it to user
 *
 * @param message is a String that will be logged with @{link Level.FINE} level and printed to user's console
 */
fun print(message: String) {
    logAny(message, Level.FINEST)
}

/**
 * An auxiliary function to log fine message and print it to user
 *
 * @param message is a String that will be logged with @{link Level.INFO} level and printed to user's console
 */
fun logWarning(message: String) {
    logAny(message, Level.INFO)
}

/**
 * An auxiliary function to log error and print message to user
 *
 * @param message is a String that will be logged with @{link Level.WARNING} level and printed to user's console
 * @param exitCode is an error code that will be used to exit the application
 */
fun logError(message: String, exitCode: Int) {
    logAny("Error: $message", Level.WARNING, exitCode)
}

/**
 * An auxiliary function to consume some random integer in given range
 *
 * @param min is a lowest available integer
 * @param max is a biggest available integer
 * @return random integer between [min] and [max] inclusively
 */
fun getRandomIntInRange(min: Int, max: Int): Int {
    return ThreadLocalRandom.current().nextInt(min, max + 1)
}

/***** RESOURCES  */
object ResourcesAccessor {

    private const val RESOURCES_BUNDLE_NAME = "resources"
    val resources = ResourceBundle.getBundle(RESOURCES_BUNDLE_NAME, Locale.getDefault())!!

}