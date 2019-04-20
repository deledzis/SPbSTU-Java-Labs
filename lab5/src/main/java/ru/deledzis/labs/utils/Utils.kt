package ru.deledzis.labs.utils

import java.util.concurrent.ThreadLocalRandom
import java.util.logging.Level
import java.util.logging.Logger


/**
 * An auxiliary function to log any message with any level of importance and log this message to user
 *
 * @param message is a String that will be logged with [logLevel] and printed to user's console
 * @param logLevel level of importance of log message
 * @param exitCode is an error code that will be used to exit the application
 */
private fun logAny(message: String, logLevel: Level, exitCode: Int = 0) {
    Logger.getAnonymousLogger().log(logLevel, message)

    when(logLevel) {
        Level.FINEST    -> if (isDebug()) println(message)
        else            -> println(message)
    }

    if (exitCode != 0) {
        System.exit(exitCode)
    }
}

/**
 * An auxiliary function to log fine message and log it to user
 *
 * @param message is a String that will be logged with @{link Level.FINE} level and printed to user's console
 */
fun log(message: String) {
    logAny(message, Level.FINEST)
}

/**
 * An auxiliary function to log fine message and log it to user
 *
 * @param message is a String that will be logged with @{link Level.INFO} level and printed to user's console
 */
fun print(message: String) {
    logAny(message, Level.FINE)
}

/**
 * An auxiliary function to log error and log message to user
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
fun getRandomInt(min: Int, max: Int): Int {
    return ThreadLocalRandom.current().nextInt(min, max + 1)
}


fun isDebug() = true