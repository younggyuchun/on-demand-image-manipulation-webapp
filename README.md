# ON DEMAND IMAGE MANIPULATION APP [![Build Status](https://travis-ci.org/younggyuchun/on-demand-image-manipulation-webapp.svg?branch=master)](https://travis-ci.org/younggyuchun/on-demand-image-manipulation-webapp)
Many web or mobile app provides feature that display images. The images might be users uploaded images, product images, or static images. You probably need to manipulate images on-the-fly to fit user requirements or mobile devices.  

ON-DEMAND Image Manipulate App allows you to specify the required height and width to resize or crop images and also optimizes images to reduce file size and save bandwidth. 

The following URL example shows image resizes transformation.
```
http://yourdomain/images/v1/resize?width=500&height=200&path=flower.jpg
```

### Supported image processing application
- [GraphicsMagick](http://www.graphicsmagick.org/) (currently supported)
- [ImageMagick](http://imagemagick.org/script/index.php) (coming soon)


### Supported formats
- http://www.graphicsmagick.org/formats.html

### Features (currently)
- resize an image including animated GIFs
- optimize aan image quality

### Features (coming soon)
- fetch an image from remote locations
- composite an image
- convert an image format
- an image padding
- an image transformation(rotate, crop, flip)

### Image processing installation
- To use this on-demand a image manipulate app, GraphicsMagick 1.3.2.1 must be installed on Linux or Windows.
- To install GraphicsMagick 1.3.2.1 on Linux please see link below 
  * ftp://ftp.graphicsmagick.org/pub/GraphicsMagick/1.3/
  * http://www.graphicsmagick.org/INSTALL-unix.html
- To install GraphicsMagick 1.3.2.1 on Windows please see link below 
  * http://www.graphicsmagick.org/INSTALL-windows.html
  * http://78.108.103.11/MIRROR/ftp/GraphicsMagick/1.3/windows/
- Note that currently GraphicsMagick 1.3.2.1 is only supported.
