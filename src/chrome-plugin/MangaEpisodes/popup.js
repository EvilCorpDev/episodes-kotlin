function getLastEpisode(url) {
	$.ajaxPrefilter( function (options) {
		if (options.crossDomain && jQuery.support.cors) {
			var http = (window.location.protocol === 'http:' ? 'http:' : 'https:');
			options.url = http + '//cors-anywhere.herokuapp.com/' + options.url;
		}
	});
	$.get(url, function(data) {
		//var episode = getEpisodeReadManga($(data));
		//console.log(episode);
		//return episode;
		getEpisodeMangaFox($(data));
	});
}

function getEpisodeReadManga($data) {	
	var $link = $($data.find('.read-last').find('a')[0]);
	var href = $link.attr('href');
	return href.substr(href.lastIndexOf('/') + 1, href.number);
}

function getEpisodeMangaFox($data) {
	var $chList = $($data.find('.chlist')[0]);
	var $link = $($chList.find('h3')[0]).find('a');
	var href = $link.attr('href');
	href = href.substr(0, href.lastIndexOf('/'));
	return href.substr(href.lastIndexOf('/') + 2, href.number);
}

function getEpisodeMangaReader($data) {

}