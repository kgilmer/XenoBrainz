# A bare bones, BSD Licensed MusicBrainz WS API in Java that is Android compatible.

[![](https://jitpack.io/v/kgilmer/XenoBrainz.svg)](https://jitpack.io/#kgilmer/XenoBrainz)

# Usage

The client class is `pl.qus.xenoamp.musicbrainz.MBClient`. See unit tests for more details.

## Examples

### Basic Search

```java
MBClient client = new MBClient();
//Search for releases with a title
List<MBRelease> releases = client.searchRelease("Who can you trust?");
```

### Multi-term Search

```java
import static pl.qus.xenoamp.musicbrainz.RecordingTerm.RecordingKey.*;

MBClient client = new MBClient();
List<MBRecording> results = mbClient.searchRecording(
                        ARTIST.is("the police"),
                        RECORDING.is("mother"),
                        RELEASE.is("Synchronicity"));
```

# Notes

* Using the default HTTP resolver will pass `com.github.kgilmer:XenoBrainz:<version>` as the user agent.  If you supply your own `pl.qus.xenoamp.musicbrainz.MBClient.HTTPGetDataLoader` you must be sure to specify an appropriate user agent.
* The MusicBrainz service is rate limited.  This library implements no rate limiting logic internally, so you'll need to add that yourself according to your use case.

## Based on work of ssuukk at https://github.com/ssuukk/XenoBrainz

## Changelog

### Release 0.4
* Some more javadoc.
* Added error handler interface for HTTP and XML parsing errors.
* Specify Java 1.7 for Android compatibility.

### Release 0.3
* Bugfix

### Release 0.2
* Multi-term search support.

### Release 0.1
* Made code more idiomatic.
* Added gradle build files.
* Added a few basic unit tests.
* Removed dependency on Apache HTTP client.
* Add API to specify custom HTTP client.
* Add @Nonnull / @Nullable where appropriate
* Publish with jitpack.

# Original Notice

## XenoBrainz

MusicBrainz Java API, no strings attached

Licensed under BSD License
Copyright 2012 ssuukk

XenoBrainz is a part of XenoAmp - a slightly different music player for Android

