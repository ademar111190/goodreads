package ademar.goodreads.test

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubber

fun <T> When(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

fun <T> Stubber.When(mock: T): T = this.`when`(mock)
