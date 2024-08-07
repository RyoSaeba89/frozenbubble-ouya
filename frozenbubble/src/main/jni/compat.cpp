/*
 * This source code is public domain.
 *
 * Authors: Eric Fortin <videogameboy76@yahoo.com> (C interface wrapper)
 */

#include "stdafx.h"
#include "compat.h"

static unsigned long next = 2;

extern "C" long _do_random(unsigned long *ctx)
{
	long hi, lo, x;

	/* Must be in [1, 0x7ffffffe] range at this point. */
	hi = *ctx / 127773;
	lo = *ctx % 127773;
	x = 16807 * lo - 2836 * hi;
	if (x < 0)
		x += 0x7fffffff;
	*ctx = x;
	/* Transform to [0, 0x7ffffffd] range. */
	return (x - 1);
}

extern "C" void _srandom( unsigned long seed )
{
   next = seed;
   next = (next % 0x7ffffffe) + 1;
}

extern "C" long _random()
{
	return (_do_random(&next));
}

/*
 * __strncpy_chk2
 *
 * This is a variant of __strncpy_chk, but it also checks to make
 * sure we don't read beyond the end of "src". The code for this is
 * based on the original version of strncpy, but modified to check
 * how much we read from "src" at the end of the copy operation.
 */
extern "C" char* __strncpy_chk2(char* __restrict dst, const char* __restrict src,
              size_t n, size_t dest_len, size_t src_len)
{
  if (n != 0) {
    char* d = dst;
    const char* s = src;
    do {
      if ((*d++ = *s++) == 0) {
        /* NUL pad the remaining n-1 bytes */
        while (--n != 0) {
          *d++ = 0;
        }
        break;
      }
    } while (--n != 0);
    size_t s_copy_len = static_cast<size_t>(s - src);
  }
  return dst;
}
