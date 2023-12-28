package com.wikimedia.utils;

import com.wikimedia.entities.WikiMediaData;
import com.wikimedia.models.WikiMedia;

public class TransformerUtils {

    public TransformerUtils() {

    }

    /**
     * Transforms a WikiMedia object to WikiMediaData.
     *
     * @param wikiMedia The WikiMedia object to transform.
     * @return The corresponding WikiMediaData.
     */
    public static WikiMediaData transformToWikiMediaData(WikiMedia wikiMedia) {
        return WikiMediaData.builder().meta(wikiMedia.getMeta()).type(wikiMedia.getType()).namespace(wikiMedia.getNamespace()).title(wikiMedia.getTitle()).titleUrl(wikiMedia.getTitleUrl()).comment(wikiMedia.getComment()).timestamp(wikiMedia.getTimestamp()).user(wikiMedia.getUser()).bot(wikiMedia.getBot()).notifyUrl(wikiMedia.getNotifyUrl()).serverUrl(wikiMedia.getServerUrl()).serverName(wikiMedia.getServerName()).serverScriptPath(wikiMedia.getServerScriptPath()).wiki(wikiMedia.getWiki()).parsedcomment(wikiMedia.getParsedcomment()).build();
    }
}
