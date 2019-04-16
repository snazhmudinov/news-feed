# news-feed
Sample app - retrieves and displays top headlines about the US, using Google news API. 
Tap any headline to open the details in your browser.

Before installation:
Make sure you add your Google news API key in app's `build.gradle` file, where it says `YOUR_API_KEY`. 

Note: Currently we only fetch max 105 articles (this is a free plan quota). To be able to pull more data from the API, please provide the `API_KEY` for the paid plan. 
