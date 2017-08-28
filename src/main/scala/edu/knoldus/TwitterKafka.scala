package edu.knoldus

import twitter4j._
import scala.collection.JavaConverters._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TwitterKafka {

  val twitter: Twitter = TwitterFactory.getSingleton

  def getTweetsOfUser(userName: String): Future[List[String]] = {

    Future {
      twitter.getUserTimeline(userName).asScala.toList.flatMap{ result =>
        result.getHashtagEntities.toList.map(_.getText)
      }
    }
  }

  def tweetOnBasisOfHashtag(hashtag: String): Future[List[String]] = {
    Future {
      println("123")

      val query = new Query(hashtag)

      val result = twitter.search(query)

      val resultList = result.getTweets.asScala.toList
      println("Returning future of list of string from method in twitter")
      resultList.map(status => status.getHashtagEntities.toString)

    }

  }

}
