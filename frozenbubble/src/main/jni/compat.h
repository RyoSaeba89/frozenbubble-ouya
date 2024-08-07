/*
 * This source code is public domain.
 *
 * Authors: Eric Fortin <videogameboy76@yahoo.com> (C interface wrapper)
 */

#ifndef COMPAT_H__INCLUDED
#define COMPAT_H__INCLUDED

#ifdef __cplusplus
extern "C" {
#endif

/*
 * These functions are normally found in the C standard library, but
 * are absent from various Android versions.
 */
extern "C" long _random();
extern "C" void _srandom( unsigned long seed );
extern "C" char* __strncpy_chk2(char* __restrict dst, const char* __restrict src,
              size_t n, size_t dest_len, size_t src_len);

#ifdef __cplusplus
} /* extern "C" */
#endif

#endif
