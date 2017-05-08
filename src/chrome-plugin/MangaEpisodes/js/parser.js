$(function () {
    $('.add-btn').click(function () {
        addManga();
    });
    $('.refresh-btn').click(function () {
        refreshManga();
    });
});

function addManga() {
    var url = $('.new-manga').val();
    var newMangaSelector = '.new-manga';
    if (url.length > 0) {
        addNewManga(url);
        $(newMangaSelector).val('');
        $(newMangaSelector).removeClass('empty-input');
    } else {
        $(newMangaSelector).addClass('empty-input');
    }
}

function refreshManga() {
    $('.manga-container').empty();
    updateLocalStorage(showMangaEpisodesFromStorage);
}

function updateLocalStorage(callback) {
    $.get(options.serverUrl + '/episodes', function (data) {
        localStorage.setItem('allManga', JSON.stringify(data));
        if (callback) {
            callback();
        }
    });
}

function delManga($element) {
    var title = $element.find('a').text();
    $.ajax({
        url: options.serverUrl + '/episodes?title=' + title.replace(/\s+/g, '_'),
        type: 'DELETE',
        success: function () {
            deleteMangaFromStorage(title, $element);
        }
    });
}

function deleteMangaFromStorage(title, $element) {
    $element.remove();
    var deletedElement;
    var allManga = JSON.parse(localStorage.getItem('allManga'));
    for (var i = 0; i < allManga.length; i++) {
        if (allManga[i].title === title) {
            deletedElement = allManga[i];
            allManga.splice(i, 1);
            break;
        }
    }
    localStorage.setItem('allManga', JSON.stringify(allManga));
    if(deletedElement.isNew) {
        changeNewCount(false);
    }
}

function readManga($element) {
    var title = $element.find('a').text();
    var titleWithoutSpaces = title.replace(/\s+/g, '_');
    $.ajax({
        url: options.serverUrl + format('/episodes/{title}/read', {title: titleWithoutSpaces}),
        type: 'PUT',
        success: function () {
            updateLocalStorageIsNew(title, false);
            $element.removeClass('list-element__new-episode');
            $element.find('.read-btn').hide();
            changeNewCount(false);
        }
    });
}

function updateLocalStorageIsNew(title, isNew) {
    var allManga = JSON.parse(localStorage.getItem('allManga'));
    for (var i = 0; i < allManga.length; i++) {
        if (allManga[i].title === title) {
            allManga[i].isNew = isNew;
            break;
        }
    }
    localStorage.setItem('allManga', JSON.stringify(allManga));
}

function changeNewCount(increase) {
    chrome.browserAction.getBadgeText({}, function (text) {
        var num = parseInt(text);
        if (increase) {
            num++;
            chrome.browserAction.setBadgeText({text: num.toString()});
        } else {
            num--;
            chrome.browserAction.setBadgeText({text: num.toString()});
        }
    });
}

function addNewManga(url) {
    $.ajax({
        url: options.serverUrl + '/episodes',
        type: 'POST',
        data: {mangaUrl: url},
        success: function (data) {
            var allManga = JSON.parse(localStorage.getItem('allManga'));
            allManga.push(data);
            localStorage.setItem('allManga', JSON.stringify(allManga));
            appendElement(data);
            changeNewCount(true);
        }
    });
}

function format(str, args) {
    return str.replace(/{(\w+)}/g, function (m, n) {
        return args[n] ? args[n] : m;
    });
}